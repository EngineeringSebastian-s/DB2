import Navbar from "./Navbar/Navbar.tsx";
import {LogoutOutlined} from "@ant-design/icons";
import {useAuthContext} from "../contexts/AuthContext.tsx";
import {ProductOutlined, HomeOutlined} from "@ant-design/icons";

export default function Header() {

    const icons = [HomeOutlined, ProductOutlined];
    const labels = ["Inicio", "Productos","Productos Disponibles"];
    const paths = ["/home", "/products", "/availableProducts"];

    const { logout } = useAuthContext();
    return (
        <>
            <style>{`
                .navbar {
                    background-color: #06524B;
                    color: white;
                }
                .navbar .nav-link {
                    color: #C6CDC0;
                    font-weight: 600;
                }
                .navbar .nav-link:hover {
                    color: #C6CDC0;
                }
            `}</style>

            <div className="navbar">
                <div className="container">
                    <header className="d-flex justify-content-center py-3">
                        <Navbar icons={icons} labels={labels} paths={paths}/>
                        <button className="logout" onClick={logout}><LogoutOutlined/></button>
                    </header>
                </div>
            </div>
        </>
    )
}
