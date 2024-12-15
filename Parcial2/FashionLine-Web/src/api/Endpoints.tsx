// URL base desde el archivo .env
const BASE_URL = import.meta.env.VITE_API_BASE_URL;

// Auth
export const auth = `${BASE_URL}/auth/login`;
export const verifyToken = `${BASE_URL}/auth/verify`;