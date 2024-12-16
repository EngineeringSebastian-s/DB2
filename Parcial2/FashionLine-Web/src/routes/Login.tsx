import { FormEvent, SyntheticEvent, useEffect, useState } from "react";
import { useAuthContext } from "../contexts/AuthContext";
import { useNavigate } from "react-router-dom";
import Loading from "../components/Loading";

export default function Login() {

    const { login, isAuthenticated, error, loading } = useAuthContext();
    const navigate = useNavigate();

    const [email, setEmail] = useState<string>("");
    const [password, setPassword] = useState<string>("");

    useEffect(() => {
        if (isAuthenticated) {
            navigate("/home")
        }
    }, [isAuthenticated]);

    const handleChangeEmail = (e: FormEvent<HTMLInputElement>) => {
        setEmail(e.currentTarget.value);
    };

    const handleChangePassword = (e: FormEvent<HTMLInputElement>) => {
        setPassword(e.currentTarget.value);
    }

    const handleSubmit = async (e: SyntheticEvent) => {
        e.preventDefault();

        await login(email, password)
            .then((r: unknown) => console.log(r))
            .catch((error: unknown) => console.log(error));
    }

    if (loading) return (<Loading />);

    return (
        <>
            <style>{`
                body {
                    background-color: #016960;
                    height: 100%;
                }
                form {
                    width: 28%;
                    margin: auto;
                    padding-top: 150px;
                }
                .bg {
                    background-color: #C6CDC0;
                }
                .btn-color {
                    background-color: #06524B;
                    border: none;
                }
            `}</style>

            <div className="container">
                <form className="form-group" onSubmit={handleSubmit}>
                    <div className="mb-3 bg p-5 rounded">
                        <h2 className="text-center">Login</h2>

                        {/* Email Field */}
                        <label htmlFor="email" className="form-label mt-4 fw-semibold">Email address</label>
                        <input
                            type="email"
                            className="form-control"
                            id="email"
                            placeholder="name@example.com"
                            value={email}
                            onChange={handleChangeEmail}
                        />

                        {/* Password Field */}
                        <label htmlFor="password" className="form-label mt-3 fw-semibold">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            value={password}
                            onChange={handleChangePassword}
                        />

                        {/* Remember Me */}
                        <div className="form-check">
                            <input className="form-check-input" type="checkbox" id="rememberMe" />
                            <label className="form-check-label" htmlFor="rememberMe">Remember me</label>
                        </div>

                        {/* Error Message */}
                        {error && <p className="mt-10 text-center text-sm text-red-500">{error}</p>}

                        {/* Submit Button */}
                        <button type="submit" className="form-control btn-color text-light mt-3">
                            {loading ? "Loading..." : "Sign in"}
                        </button>
                    </div>
                </form>
            </div>
        </>
    )
}
