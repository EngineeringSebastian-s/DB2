import axios, {AxiosError} from "axios";
import {UserData} from "../contexts/AuthContext";
import {ProductDTO} from "../types/ApiResponse.tsx";
import {
    allCategories,
    allProducts,
    allSizes,
    createProduct,
    deleteProduct,
    productById,
    productsByCategory,
    productsByName,
    productsBySize,
    updateProduct
} from "./Endpoints";

/** Obtener todos los productos */
export async function getAllProducts(user: UserData): Promise<ProductDTO[]> {
    let products: ProductDTO[] = [];

    console.log("[API/PRODUCTS] Fetching all products");

    await axios.get(allProducts, getAuthHeaders(user))
        .then(response => {
            products = response.data;
        })
        .catch(e => console.log("Error fetching all products: " + e));

    return products;
}

/** Obtener producto por ID */
export async function getProductById(user: UserData, id: string | number): Promise<ProductDTO> {
    let product: ProductDTO | null = null;

    console.log(`[API/PRODUCTS] Fetching product by ID: ${id}`);

    await axios.get(productById(id), getAuthHeaders(user))
        .then(response => {
            product = response.data;
        })
        .catch(e => console.log("Error fetching product by ID: " + e));

    return product!;
}

/** Obtener productos por nombre */
export async function getProductsByName(user: UserData, name: string): Promise<ProductDTO[]> {
    let products: ProductDTO[] = [];

    console.log(`[API/PRODUCTS] Fetching products by name: ${name}`);

    await axios.get(productsByName(name), getAuthHeaders(user))
        .then(response => {
            products = response.data;
        })
        .catch(e => console.log("Error fetching products by name: " + e));

    return products;
}

/** Obtener productos por tamaño */
export async function getProductsBySize(user: UserData, size: string): Promise<ProductDTO[]> {
    let products: ProductDTO[] = [];

    console.log(`[API/PRODUCTS] Fetching products by size: ${size}`);

    await axios.get(productsBySize(size), getAuthHeaders(user))
        .then(response => {
            products = response.data;
        })
        .catch(e => console.log("Error fetching products by size: " + e));

    return products;
}

/** Obtener productos por categoría */
export async function getProductsByCategory(user: UserData, category: string): Promise<ProductDTO[]> {
    let products: ProductDTO[] = [];

    console.log(`[API/PRODUCTS] Fetching products by category: ${category}`);

    await axios.get(productsByCategory(category), getAuthHeaders(user))
        .then(response => {
            products = response.data;
        })
        .catch(e => console.log("Error fetching products by category: " + e));

    return products;
}

/** Obtener todas las categorías */
export async function getAllCategories(user: UserData): Promise<string[]> {
    let categories: string[] = [];

    console.log("[API/PRODUCTS] Fetching all categories");

    await axios.get(allCategories, getAuthHeaders(user))
        .then(response => {
            categories = response.data;
        })
        .catch(e => console.log("Error fetching all categories: " + e));

    return categories;
}

/** Obtener todos los tamaños */
export async function getAllSizes(user: UserData): Promise<string[]> {
    let sizes: string[] = [];

    console.log("[API/PRODUCTS] Fetching all sizes");

    await axios.get(allSizes, getAuthHeaders(user))
        .then(response => {
            sizes = response.data;
        })
        .catch(e => console.log("Error fetching all sizes: " + e));

    return sizes;
}

/** Crear un nuevo producto */
export async function createNewProduct(user: UserData, productData: ProductDTO): Promise<ProductDTO | null> {
    let newProduct: ProductDTO | null = null;

    console.log("[API/PRODUCTS] Creating a new product");

    try {
        const response = await axios.post(createProduct, productData, getAuthHeaders(user));

        if (response.status === 200) {
            newProduct = response.data;
        } else {
            console.error("Error creating product:", response.statusText);
        }
    } catch (e) {
        console.error("Error creating product: ", e);
    }

    return newProduct;
}

/** Actualizar un producto existente */
export async function updateExistingProduct(user: UserData, id: string, productData: ProductDTO): Promise<ProductDTO | null> {
    let updatedProduct: ProductDTO | null = null;

    console.log(`[API/PRODUCTS] Updating product with ID: ${id}`);

    try {
        const response = await axios.put(updateProduct(id), productData, getAuthHeaders(user));

        if (response.status === 200) {
            updatedProduct = response.data;
            console.log("Product updated successfully", updatedProduct);
        } else {
            console.error("Failed to update product", response);
            throw new Error("Error al actualizar el producto.");
        }
    } catch (e: unknown) {
        if (e instanceof Error) {
            console.error("Error updating product: ", e);
            throw new Error("Error al actualizar el producto: " + e.message);
        } else {
            console.error("Unexpected error: ", e);
            throw new Error("Error desconocido al actualizar el producto.");
        }
    }

    return updatedProduct;
}

/** Eliminar un producto */
export async function deleteProductById(user: UserData, id: string): Promise<string> {
    let responseMessage = "Producto no eliminado";
    console.log(`[API/PRODUCTS] Deleting product with ID: ${id}`);
    try {
        const response = await axios.delete(deleteProduct(id), getAuthHeaders(user));
        if (response.status === 200) {
            responseMessage = "Producto eliminado exitosamente";
        } else {
            responseMessage = "Producto no eliminado (código no esperado)";
        }
    } catch (error: unknown) {
        if (error instanceof AxiosError) {
            if (error.response && error.response.data) {
                const errorMessage = error.response.data.message || "Error desconocido";
                responseMessage = `Error: ${errorMessage}`;
            } else {
                responseMessage = "Error al eliminar el producto";
            }
        } else {
            responseMessage = "Error desconocido";
        }
    }

    return responseMessage;
}

/** Función para obtener encabezados con token */
function getAuthHeaders(user: UserData) {
    return {
        headers: {
            Authorization: "Bearer " + user.authToken,
        }
    };
}
