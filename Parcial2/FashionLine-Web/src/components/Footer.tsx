export default function Footer() {

    return (
        <>
            <style>{`
                .footer {
                    background-color: #06524B;
                    color: white;
                    padding: 20px 0;
                       height: 12vh;
                    align-items: center;
                    justify-content: center;
                    display: flex;
                }
                .footer .nav-link {
                    color:  #C6CDC0;
                    font-weight: 600;
                }
                .footer .nav-link:hover {
                    color: #C6CDC0;
                }
            `}</style>

            <div className="footer">
                <div className="container">
                    <div className="text-center text-white">
                        <p>&copy; 2024 Your Company. All rights reserved.</p>
                    </div>
                </div>
            </div>
        </>
    )
}
