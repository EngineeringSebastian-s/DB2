// URL base desde el archivo .env
const BASE_URL = import.meta.env.VITE_API_BASE_URL;

// Auth
export const auth = `${BASE_URL}/auth/login`;
export const verifyToken = `${BASE_URL}/auth/verify`;

// Products
export const allProducts = `${BASE_URL}/Products/All`;
export const productById = (id: string | number) => `${BASE_URL}/Products/${id}`;
export const productsByName = (name: string) => `${BASE_URL}/Products/name/${name}`;
export const productsBySize = (size: string) => `${BASE_URL}/Products/size/${size}`;
export const productsByCategory = (category: string) => `${BASE_URL}/Products/category/${category}`;
export const allCategories = `${BASE_URL}/Products/category/All`;
export const allSizes = `${BASE_URL}/Products/size/All`;

// Create, Update, and Delete
export const createProduct = `${BASE_URL}/Products/Create`;
export const updateProduct = (id: string | number) => `${BASE_URL}/Products/Update/${id}`;
export const deleteProduct = (id: string | number) => `${BASE_URL}/Products/Delete/${id}`;