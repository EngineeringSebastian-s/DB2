import { Outlet, useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useAuthContext } from "../contexts/AuthContext.tsx";
import Login from "./Login.tsx";
import Loading from "../components/Loading.tsx";
import Header from "../components/Header.tsx";
import Footer from "../components/Footer.tsx";
import 'bootstrap/dist/css/bootstrap.min.css';
export default function Root() {
    const { isAuthenticated, loading } = useAuthContext();
    const navigate = useNavigate();

    useEffect(() => {
        if (!loading && !isAuthenticated) {
            navigate("/login");
        }
    }, [loading, isAuthenticated, navigate]);

    if (loading) return <Loading />;

    return (
        <>
            {isAuthenticated ? (
                <div
                    className="container-fluid text-light pl-5"
                    style={{
                        backgroundColor: '#016960',
                        minHeight: '100vh',
                        height: '100%',
                        padding: 0,
                        margin: 0,
                    }}
                >
                    <Header/>

                    <div
                        className="container-fluid text-light p-5"
                        style={{
                            backgroundColor: '#016960',
                            minHeight: '80vh',
                            height: '100%',
                            display: 'flex',
                            flexDirection: 'column',
                            justifyContent: 'space-between',
                        }}
                    >
                        <div className="grid text-center container-sm">
                            <Outlet/>
                        </div>
                    </div>
                    <Footer/>
                </div>
            ) : (
                <Login/>
            )}
        </>
    );
}
