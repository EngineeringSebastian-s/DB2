import {createBrowserRouter, RouterProvider} from 'react-router-dom'
import Root from '../routes/Root.tsx'

import '../assets/styles/App.css'
import '../routes/ErrorPage.tsx'
import ErrorPage from "../routes/ErrorPage.tsx";
import Login from "../routes/Login.tsx"
import Home from "../routes/Home.tsx"
import {AuthProvider} from "../contexts/AuthContext.tsx";

function App() {

    const router = createBrowserRouter([
        {
            path: "/login",
            element: <Login />,
            errorElement: <ErrorPage/>
        },
        {
            id: "app",
            path: "/",
            element: <Root/>,
            errorElement: <ErrorPage/>,
            children: [
                {
                    path: "/home",
                    element: <Home/>
                }
            ],
        },
    ]);

    return (
        <>
            <AuthProvider>
                <RouterProvider router={router}/>
            </AuthProvider>
        </>
    )
}

export default App
