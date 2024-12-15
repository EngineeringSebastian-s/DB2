import axios from "axios";
import {createContext, ReactNode, useContext, useEffect, useState} from "react";
import {auth, verifyToken} from "../api/Endpoints";

export type authContextType = {
    user: UserData | null;
    isAuthenticated: boolean;
    login: (email: string, password: string) => Promise<void>;
    logout: () => void;
    loading: boolean;
    error: string | null;
};

export type UserData = {
    authToken: string;
    id: string;
};

const defaultValues: authContextType = {
    user: null,
    isAuthenticated: false,
    login: async () => {
        throw new Error("Function not implemented.")
    },
    logout: () => {
    },
    loading: false,
    error: null,
};

const AuthContext = createContext<authContextType>(defaultValues);

export function useAuthContext() {
    return useContext(AuthContext);
}

type Props = {
    children: ReactNode;
}

export function AuthProvider({children}: Props) {
    const [user, setUser] = useState<UserData | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);
    const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

    // Load user from localStorage if available
    useEffect(() => {
        setLoading(true);
        const storedToken = localStorage.getItem("authToken");
        if (storedToken) {
            verifyStoredToken(storedToken);
        } else {
            setLoading(false)
        }
    }, []);
    const verifyStoredToken = async (token: string) => {
        try {
            setLoading(true);
            const responseUser = await axios.get(verifyToken, {
                headers: {
                    Authorization: `Bearer ${token}`,
                },
            });

            if (responseUser.data) {
                setUser({
                    authToken: token,
                    id: responseUser.data.id,
                });
                setIsAuthenticated(true);
            }
        } catch (err) {
            console.error("Error verifying token:", err);
            logout(); // Logout if token verification fails
        } finally {
            setLoading(false);
        }
    };

    const login = async (email: string, password: string) => {
        setLoading(true);
        setError(null); // Reset errors on each attempt
        try {
            const response = await axios.post(auth, {email, password});
            const authToken = response.data;

            if (!authToken) {
                throw new Error("No token received from the server.");
            }

            const responseUser = await axios.get(verifyToken, {
                headers: {Authorization: `Bearer ${authToken}`},
            });

            const userData: UserData = {
                authToken,
                id: responseUser.data.id,
            };

            // Set user and token in state and localStorage
            setUser(userData);
            setIsAuthenticated(true);
            localStorage.setItem("authToken", authToken);
        } catch (err: unknown) {
            console.error("Login error:", err);

            if (err instanceof Error) {
                setError(err.message || "An error occurred during login.");
            } else if (axios.isAxiosError(err) && err.response) {
                setError(err.response.data.message || "An error occurred during login.");
            } else {
                setError("An unknown error occurred.");
            }
        } finally {
            setLoading(false);
        }
    };

    const logout = () => {
        setUser(null);
        setIsAuthenticated(false);
        localStorage.removeItem("authToken");
    };

    const values: authContextType = {
        user,
        isAuthenticated,
        login,
        logout,
        loading,
        error,
    };

    return (
        <AuthContext.Provider value={values}>
            {children}
        </AuthContext.Provider>
    );
}
