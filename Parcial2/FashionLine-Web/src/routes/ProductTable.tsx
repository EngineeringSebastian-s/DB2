import React, {useEffect, useState} from 'react';
import {getAllProducts} from '../api/Api';
import {useAuthContext} from "../contexts/AuthContext";
import { handleCreate, handleEdit, handleDelete } from '../utils/Crud.tsx';
import {ProductDTO} from "../types/ApiResponse.tsx";
import Loading from "../components/Loading.tsx";

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
    const [error] = useState<string>('');
    const { user, loading } = useAuthContext();

    useEffect(() => {
        const fetchProducts = async () => {
            if (user?.id) {
                setLoadingData(true);
                try {
                    const data: ProductDTO[] = await getAllProducts(user);
                    const mappedProducts: ProductTable[] = data.map((product) => ({
                        id: product.id ?? '',
                        name: product.name,
                        description: product.description,
                        price: product.price,
                        stock: product.stock,
                        category: product.category,
                        size: product.size,
                    }));
                    setProducts(mappedProducts);
                } catch (error) {
                    console.error("Error fetching products: ", error);
                } finally {
                    setLoadingData(false);
                }
            }
        };

        fetchProducts();
    }, [user]);

    if (loading || loadingLogin || loadingData) {
        return <Loading />;
    }

    if (error) {
        return <div>{error}</div>;
    }


    return (
        <>
            <h2 className="h2 mb-4" style={{color: '#06524B'}}>Product List</h2>
            <div className="mb-4">
                <button className="btn" style={{backgroundColor: '#C6CDC0', color: '#fff'}} onClick={ () => handleCreate(user, setProducts)}>
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
                                onClick={() => handleEdit(user,product.id, product.name, product.description, product.price, product.stock, product.category, product.size,setProducts)}
                            >
                                Edit
                            </button>
                            <button
                                className="btn btn-danger"
                                onClick={() => handleDelete(user,product.id, setProducts)}
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
