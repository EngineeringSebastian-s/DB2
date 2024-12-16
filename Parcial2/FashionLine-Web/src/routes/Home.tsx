import icon from "../assets/images/icon.png"

export default function Home() {
    return (
        <>
            <div className="px-4 py-5 my-5 text-center">
                <img alt="" className="d-block mx-auto mb-4" height="100%" src={icon}  width="200"/>
                <h1 className="display-5 fw-bold">FashionLine</h1>
                <div className="col-lg-6 mx-auto">
                    <p className="lead mb-4">nos dedicamos a ofrecerte las últimas tendencias en moda, pero sabemos que la clave para brindarte una experiencia excepcional es mantener un inventario bien gestionado. Por eso, estamos trabajando en mejorar la forma en que organizamos nuestros productos para asegurarnos de que siempre encuentres lo que buscas..</p>
                    <div className="d-grid gap-2 d-sm-flex justify-content-sm-center">
                        <button className="btn btn-secondary btn-lg px-4 gap-3" type="button">Bienvenido</button>
                    </div>
                </div>
            </div>
        </>
    )
}