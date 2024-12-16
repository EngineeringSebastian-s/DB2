import React, { useEffect, useState } from 'react';
import { getAllProducts } from '../api/Api';
import {useAuthContext} from "../contexts/AuthContext";

interface ProductTable {
    id: string;
    name: string;
    price: number;
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

    return (
        <div>
            <h2 className="text-2xl font-semibold mb-4">Product List</h2>
            <table className="min-w-full border-collapse text-sm">
                <thead className="bg-gray-100">
                <tr>
                    <th className="px-6 py-4 text-left text-sm font-semibold text-gray-700">ID</th>
                    <th className="px-6 py-4 text-left text-sm font-semibold text-gray-700">Name</th>
                    <th className="px-6 py-4 text-left text-sm font-semibold text-gray-700">Price</th>
                    <th className="px-6 py-4 text-left text-sm font-semibold text-gray-700">Category</th>
                    <th className="px-6 py-4 text-left text-sm font-semibold text-gray-700">Size</th>
                </tr>
                </thead>
                <tbody className="bg-white">
                {products.map((product) => (
                    <tr key={product.id} className="border-t">
                        <td className="px-6 py-4 text-gray-700">{product.id}</td>
                        <td className="px-6 py-4 text-gray-700">{product.name}</td>
                        <td className="px-6 py-4 text-gray-700">{product.price}</td>
                        <td className="px-6 py-4 text-gray-700">{product.category}</td>
                        <td className="px-6 py-4 text-gray-700">{product.size}</td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default ProductsTable;
