import { useEffect, useState } from 'react';
import type { InventorResponse } from '../../types/types';
import axios from 'axios';
import {useNavigate, useParams} from 'react-router-dom';
import Layout from "../../layouts/layout.tsx";
import FormLayout from "../../layouts/formLayout.tsx";

export default function InventorDeleteView() {
    const { id } = useParams();
    const navigate = useNavigate();
    const [inventor, setInventor] = useState<InventorResponse | null>(null);
    useEffect(() => {
        axios.get<InventorResponse>(`http://localhost:8080/api/inventors/${id}`).then((resp) => {
            setInventor({
                ...resp.data,
            });
        });
    }, []);

    const [result, setResult] = useState<number>(0);
    const onSubmit = async () => {
        const resp = await axios.delete(`http://localhost:8080/api/inventors/${id}`);
        setResult(resp.status);
    };

    return (
        <Layout>
            <FormLayout name="Delete Inventor">
                {result === 204 && (
                    <div className="alert alert-success mt-4 mb-0">
                        The inventor was deleted successfully.
                    </div>
                )}
                <div className="alert alert-danger">
                    <h5 className="alert-heading mb-2">
                        Confirm deletion
                    </h5>

                    <p className="mb-0">
                        You are about to permanently delete this inventor.
                        This action cannot be undone.
                    </p>
                </div>

                <form
                    onSubmit={async (e) => {
                        e.preventDefault();
                        await onSubmit();
                    }}
                >
                    <div className="card bg-light border-0 mb-4">
                        <div className="card-body">
                            <div className="row g-3">
                                <div className="col-md-4">
                                    <div className="text-secondary small fw-semibold">
                                        Name
                                    </div>

                                    <div className="fs-5">
                                        {inventor?.name ?? "-"}
                                    </div>
                                </div>

                                <div className="col-md-4">
                                    <div className="text-secondary small fw-semibold">
                                        Born At
                                    </div>

                                    <div>
                                        {inventor?.bornat ?? "-"}
                                    </div>
                                </div>

                                <div className="col-md-4">
                                    <div className="text-secondary small fw-semibold">
                                        Died At
                                    </div>

                                    <div>
                                        {inventor?.diedat ?? "-"}
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div className="d-flex justify-content-between">
                        <button
                            type="button"
                            className="btn btn-outline-secondary"
                            onClick={() => navigate("/crud/inventors")}
                        >
                            Back
                        </button>

                        <button
                            className="btn btn-danger"
                            type="submit"
                        >
                            Delete Inventor
                        </button>
                    </div>
                </form>
            </FormLayout>
        </Layout>
    );
}
