import React, {useEffect, useState} from 'react';
import {deleteProductById, getAllProducts, updateExistingProduct} from '../api/Api';
import {useAuthContext} from "../contexts/AuthContext";
import Swal from 'sweetalert2';

interface ProductTable {
    id: string;
    name: string;
    description:string;
    price: string;
    stock: string;
    category: string;
    size: string;

}

const ProductsTable: React.FC = () => {
    const [products, setProducts] = useState<ProductTable[]>([]);
    const [loadingData, setLoadingData] = useState<boolean>(true);
    const [loadingLogin] = useState<boolean>(false);
    const [error, setError] = useState<string>('');
    const { user, loading } = useAuthContext();

    useEffect(() => {
        const fetchProducts = async () => {
            if (user?.id) {
                setLoadingData(true);
                try {
                    const data = await getAllProducts(user);
                    setProducts(data);
                } catch (error) {
                    console.error("Error fetching products: ", error);
                } finally {
                    setLoadingData(false);
                }
            }
        };

        fetchProducts();
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    if (error) {
        return <div>{error}</div>;
    }

    const handleCreate = () => {
        console.log("Create Product clicked");
        // Lógica para crear un producto
    };
    const handleEdit = async (
        id: string,
        name: string,
        description: string,
        price: string,
        stock: string,
        category: string,
        size: string
    ) => {
        if (!user) {
            Swal.fire({
                title: '¡Error!',
                text: 'No estás autenticado, por favor inicia sesión.',
                icon: 'error',
                confirmButtonText: 'Aceptar'
            });
            return;
        }

        try {
            // Convertimos price y stock a números para asegurarnos de que sean tratados como números en el formulario
            const priceAsNumber = parseFloat(price);
            const stockAsNumber = parseInt(stock);

            const { value: formData } = await Swal.fire({
                title: "Edit Product",
                html: `
                <input id="swal-input1" class="swal2-input" value="${name}" placeholder="Name">
                <input id="swal-input2" class="swal2-input" value="${description}" placeholder="Description">
                <input id="swal-input3" class="swal2-input" type="number" value="${priceAsNumber}" placeholder="Price">
                <input id="swal-input4" class="swal2-input" type="number" value="${stockAsNumber}" placeholder="Stock">
                <input id="swal-input5" class="swal2-input" value="${category}" placeholder="Category">
                <input id="swal-input6" class="swal2-input" value="${size}" placeholder="Size">
            `,
                focusConfirm: false,
                preConfirm: () => {
                    return {
                        name: (document.getElementById("swal-input1") as HTMLInputElement).value,
                        description: (document.getElementById("swal-input2") as HTMLInputElement).value,
                        price: (document.getElementById("swal-input3") as HTMLInputElement).value,
                        stock: (document.getElementById("swal-input4") as HTMLInputElement).value,
                        category: (document.getElementById("swal-input5") as HTMLInputElement).value,
                        size: (document.getElementById("swal-input6") as HTMLInputElement).value
                    };
                },
                showCancelButton: true,
                cancelButtonText: "Cancel",
                confirmButtonText: "Save"
            });

            if (formData) {
                const updatedProductData = {
                    id,
                    name: formData.name,
                    description: formData.description,
                    price: formData.price.toString(), // price se envía como string
                    stock: formData.stock.toString(), // stock se envía como string
                    category: formData.category,
                    size: formData.size
                };

                // Llamada a la API para actualizar el producto
                const response = await updateExistingProduct(user, id, updatedProductData);

                if (response) {
                    Swal.fire({
                        title: "Success!",
                        text: "Product updated successfully.",
                        icon: "success",
                        confirmButtonText: "Ok"
                    });
                } else {
                    // En caso de error, mostramos un mensaje detallado
                    Swal.fire({
                        title: "Error!",
                        text: "There was a problem updating the product. Please try again.",
                        icon: "error",
                        confirmButtonText: "Try Again"
                    });
                }
            }
        } catch (error) {
            console.error("Error updating product:", error);
            // En caso de error en la solicitud
            Swal.fire({
                title: "Error!",
                text: "Something went wrong while updating the product. Please try again later.",
                icon: "error",
                confirmButtonText: "Ok"
            });
        }
    };


    const handleDelete = async (id: string) => {
        if (!user) {
            Swal.fire({
                title: '¡Error!',
                text: 'No estás autenticado, por favor inicia sesión.',
                icon: 'error',
                confirmButtonText: 'Aceptar'
            });
            return;
        }

        try {
            const response = await deleteProductById(user, id);
            if (response) {
                // Eliminar localmente el producto de la lista
                setProducts(prevProducts => prevProducts.filter(product => product.id !== id));
                Swal.fire({
                    title: '¡Éxito!',
                    text: 'Producto eliminado con éxito.',
                    icon: 'success',
                    confirmButtonText: 'Aceptar'
                });
            } else {
                Swal.fire({
                    title: '¡Error!',
                    text: 'Hubo un problema al eliminar el producto.',
                    icon: 'error',
                    confirmButtonText: 'Aceptar'
                });
            }
        } catch (error) {
            console.error('Error al eliminar el producto:', error);
            Swal.fire({
                title: '¡Error!',
                text: 'Hubo un error al intentar eliminar el producto.',
                icon: 'error',
                confirmButtonText: 'Aceptar'
            });
        }
    };



    return (
        <>
            <h2 className="h2 mb-4" style={{color: '#06524B'}}>Product List</h2>
            <div className="mb-4">
                <button className="btn" style={{backgroundColor: '#C6CDC0', color: '#fff'}} onClick={handleCreate}>
                    Create Product
                </button>
            </div>
            <table className="table" style={{border: '1px solid #C6CDC0', borderRadius: '8px'}}>
                <thead style={{backgroundColor: '#06524B', color: '#fff'}}>
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Category</th>
                    <th scope="col">Size</th>
                    <th scope="col">Stock</th>
                    <th scope="col">Actions</th>
                </tr>
                </thead>
                <tbody>
                {products.map((product) => (
                    <tr key={product.id} style={{backgroundColor: '#C6CDC0'}}>
                        <td>{product.id}</td>
                        <td>{product.name}</td>
                        <td>{product.price}</td>
                        <td>{product.category}</td>
                        <td>{product.size}</td>
                        <td>{product.stock}</td>
                        <td>
                            <button
                                className="btn btn-warning me-2"
                                onClick={() => handleEdit(product.id, product.name, product.description, product.price, product.stock, product.category, product.size)}
                            >
                                Edit
                            </button>
                            <button
                                className="btn btn-danger"
                                onClick={() => handleDelete(product.id)}
                            >
                                Delete
                            </button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </>
    );
};

export default ProductsTable;
