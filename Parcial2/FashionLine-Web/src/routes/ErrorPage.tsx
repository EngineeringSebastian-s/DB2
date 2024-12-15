import { useRouteError } from "react-router-dom";

export default function ErrorPage() {
    const error: unknown = useRouteError();

    if (error instanceof Error) {
        console.error(error);
    } else {
        console.error("Unknown error", error);
    }

    return (
        <div id="error-page">
            <h1>Oops!</h1>
            <p>Sorry, an unexpected error has occurred.</p>
            <p>
                <i>{error instanceof Error ? error.message : "Unknown error"}</i>
            </p>
        </div>
    );
}
