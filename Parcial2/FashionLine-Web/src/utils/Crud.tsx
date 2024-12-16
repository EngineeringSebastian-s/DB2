// src/utils/Crud.tsx
import Swal from 'sweetalert2';
import {createNewProduct, deleteProductById, updateExistingProduct} from '../api/Api';
import {ProductDTO, UserData} from '../types/ApiResponse.tsx';

export const handleCreate = async (user: UserData | null, setProducts: React.Dispatch<React.SetStateAction<any[]>>) => {
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
        const {value: formData} = await Swal.fire({
            title: "Create New Product",
            html: `
                <input id="swal-input1" class="swal2-input" placeholder="Name">
                <input id="swal-input2" class="swal2-input" placeholder="Description">
                <input id="swal-input3" class="swal2-input" type="number" placeholder="Price">
                <input id="swal-input4" class="swal2-input" type="number" placeholder="Stock">
                <input id="swal-input5" class="swal2-input" placeholder="Category">
                <input id="swal-input6" class="swal2-input" placeholder="Size">
            `,
            focusConfirm: false,
            preConfirm: () => ({
                name: (document.getElementById("swal-input1") as HTMLInputElement).value,
                description: (document.getElementById("swal-input2") as HTMLInputElement).value,
                price: (document.getElementById("swal-input3") as HTMLInputElement).value,
                stock: (document.getElementById("swal-input4") as HTMLInputElement).value,
                category: (document.getElementById("swal-input5") as HTMLInputElement).value,
                size: (document.getElementById("swal-input6") as HTMLInputElement).value
            }),
            showCancelButton: true,
            cancelButtonText: "Cancel",
            confirmButtonText: "Create"
        });

        if (formData) {
            const newProductData: ProductDTO = {
                name: formData.name,
                description: formData.description,
                price: formData.price.toString(),
                stock: formData.stock.toString(),
                category: formData.category,
                size: formData.size
            };

            const response = await createNewProduct(user, newProductData);

            if (response) {
                setProducts((prevProducts) => [...prevProducts, response]);
                Swal.fire({
                    title: "Success!",
                    text: "Product created successfully.",
                    icon: "success",
                    confirmButtonText: "Ok"
                });
            } else {
                Swal.fire({
                    title: "Error!",
                    text: "There was a problem creating the product. Please try again.",
                    icon: "error",
                    confirmButtonText: "Try Again"
                });
            }
        }
    } catch (error) {
        console.error("Error creating product:", error);
        Swal.fire({
            title: "Error!",
            text: "Something went wrong while creating the product. Please try again later.",
            icon: "error",
            confirmButtonText: "Ok"
        });
    }
};
export const handleEdit = async (
    user: UserData | null,
    id: string,
    name: string,
    description: string,
    price: string,
    stock: string,
    category: string,
    size: string,
    setProducts: React.Dispatch<React.SetStateAction<any[]>>
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
        const priceAsNumber = parseFloat(price);
        const stockAsNumber = parseInt(stock);

        const {value: formData} = await Swal.fire({
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
            preConfirm: () => ({
                name: (document.getElementById("swal-input1") as HTMLInputElement).value,
                description: (document.getElementById("swal-input2") as HTMLInputElement).value,
                price: (document.getElementById("swal-input3") as HTMLInputElement).value,
                stock: (document.getElementById("swal-input4") as HTMLInputElement).value,
                category: (document.getElementById("swal-input5") as HTMLInputElement).value,
                size: (document.getElementById("swal-input6") as HTMLInputElement).value
            }),
            showCancelButton: true,
            cancelButtonText: "Cancel",
            confirmButtonText: "Save"
        });

        if (formData) {
            const updatedProductData: ProductDTO = {
                id,
                name: formData.name,
                description: formData.description,
                price: formData.price.toString(),
                stock: formData.stock.toString(),
                category: formData.category,
                size: formData.size
            };

            const response = await updateExistingProduct(user, id, updatedProductData);

            if (response) {
                setProducts((prevProducts) =>
                    prevProducts.map((product) =>
                        product.id === id ? {...product, ...updatedProductData} : product
                    )
                );
                Swal.fire({
                    title: "Success!",
                    text: "Product updated successfully.",
                    icon: "success",
                    confirmButtonText: "Ok"
                });
            } else {
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
        Swal.fire({
            title: "Error!",
            text: "Something went wrong while updating the product. Please try again later.",
            icon: "error",
            confirmButtonText: "Ok"
        });
    }
};

export const handleDelete = async (user: UserData | null, id: string, setProducts: React.Dispatch<React.SetStateAction<any[]>>) => {
    if (!user) {
        Swal.fire({
            title: '¡Error!',
            text: 'No estás autenticado, por favor inicia sesión.',
            icon: 'error',
            confirmButtonText: 'Aceptar'
        });
        return;
    }

    const result = await Swal.fire({
        title: '¿Estás seguro?',
        text: 'Este producto será eliminado permanentemente.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar',
        confirmButtonColor: '#d33', // Color rojo para el botón de confirmación
        cancelButtonColor: '#3085d6' // Color azul para el botón de cancelación
    });
    if (result.isConfirmed) {
        try {
            const response = await deleteProductById(user, id);
            if (response) {
                setProducts((prevProducts) => prevProducts.filter((product) => product.id !== id));
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
    }
};
