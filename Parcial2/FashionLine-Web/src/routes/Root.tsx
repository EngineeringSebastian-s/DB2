import {Outlet, useNavigate} from "react-router-dom";
import SessionBar from "../components/SessionBar";
import {useEffect} from "react";
import {useAuthContext} from "../contexts/AuthContext.tsx";
import Login from "./Login.tsx";
import Loading from "../components/Loading.tsx";


export default function Root() {

    const {isAuthenticated, loading} = useAuthContext();
    const navigate = useNavigate();

    useEffect(() => {
        if (!loading && !isAuthenticated) {
            navigate("/login");
        }
    }, [loading, isAuthenticated, navigate]);


    if (loading) return (<Loading/>);

    return (
        <>
            {
                isAuthenticated ?
                    (
                        <div className="content">
                            <SessionBar/>
                            <Outlet/>
                        </div>
                    )
                    :
                    (
                        <Login/>
                    )
            }

        </>
    );
}