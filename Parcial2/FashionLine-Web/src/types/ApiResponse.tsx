export type userDTO = {
    id?: string | null;
    email: string | null,
    password: string | null
}

export type ProductDTO = {
    id?: string | null;
    name: string;
    description: string;
    price: string;
    stock: string;
    category: string;
    size: string;
};

export type UserData = {
    authToken: string;
    id: string;
};