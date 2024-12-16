import axios from "axios";
import { UserData } from "../contexts/AuthContext";
import { ProductDTO } from "../types/ApiResponse.tsx";
import {
    allProducts,
    productById,
    productsByName,
    productsBySize,
    productsByCategory,
    allCategories,
    allSizes
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

/** Función para obtener encabezados con token */
function getAuthHeaders(user: UserData) {
    return {
        headers: {
            Authorization: "Bearer " + user.authToken,
        }
    };
}
