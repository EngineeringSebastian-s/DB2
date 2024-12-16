export default function Navbar() {

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
                        <ul className="nav nav-pills">
                            <li className="nav-item">
                                <a href="#" className="nav-link active" aria-current="page">Home</a>
                            </li>
                            <li className="nav-item">
                                <a href="/Products" className="nav-link">Productos</a>
                            </li>
                            <li className="nav-item">
                                <a href="#" className="nav-link">Pricing</a>
                            </li>
                            <li className="nav-item">
                                <a href="#" className="nav-link">FAQs</a>
                            </li>
                            <li className="nav-item">
                                <a href="#" className="nav-link">About</a>
                            </li>
                        </ul>
                    </header>
                </div>
            </div>
        </>
    )
}
