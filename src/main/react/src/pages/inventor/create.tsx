import type {InventorRequest, InventorResponse, InventionMinimal} from '../../types/types';
import { useRef, useState } from 'react';
import axios from 'axios';
import InventionSelector from "../../components/invention/invention-selector.tsx";
import {useNavigate} from "react-router-dom";
import Layout from "../../layouts/layout.tsx";
import FormLayout from "../../layouts/formLayout.tsx";

export default function InventorCreateView() {
    const navigate = useNavigate();
    const inventorRef = useRef<InventorRequest>({
        name: '',
        bornat: 0,
        diedat: null,
        inventions: [],
    });
    const [inventions, setInventions] = useState<InventionMinimal[]>([]);
    const [result, setResult] = useState<{
        status: number;
        new: InventorResponse | null;
    }>({
        status: 0,
        new: null,
    });
    const onSubmit = async () => {
        const resp = await axios.post<InventorResponse>('http://localhost:8080/api/inventors', {
            ...inventorRef.current,
            inventions: inventions.map(i => i.id)
        });
        setResult({
            status: resp.status,
            new: { ...resp.data },
        });
    };

    return (
        <Layout>
            <FormLayout name="Create Inventor">
                {result.status === 201 && result.new && (
                    <div className="alert alert-success d-flex justify-content-between align-items-center">
                    <span>
                        The inventor was created successfully.
                    </span>
                        <button
                            type="button"
                            className="btn btn-success btn-sm"
                            onClick={() =>
                                navigate(`/crud/inventors/${result.new!.id}`)
                            }
                        >
                            View Details
                        </button>
                    </div>
                )}
                <form
                    onSubmit={async (e) => {
                        e.preventDefault();
                        await onSubmit();
                    }}
                >
                    <div className="mb-3">
                        <label className="form-label fw-semibold">
                            Name
                        </label>

                        <input
                            className="form-control"
                            type="text"
                            required
                            onChange={(e) =>
                                (inventorRef.current.name =
                                    e.currentTarget.value)
                            }
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label fw-semibold">
                            Born At
                        </label>

                        <input
                            className="form-control"
                            type="number"
                            required
                            onChange={(e) =>
                                (inventorRef.current.bornat =
                                    Number(e.currentTarget.value))
                            }
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label fw-semibold">
                            Died At
                        </label>

                        <input
                            className="form-control"
                            type="number"
                            onChange={(e) =>
                                (inventorRef.current.diedat =
                                    isNaN(e.currentTarget.valueAsNumber)
                                        ? null
                                        : e.currentTarget.valueAsNumber)
                            }
                        />
                    </div>

                    <div className="mb-4">
                        <label className="form-label fw-semibold">
                            Inventions
                        </label>

                        <InventionSelector
                            value={inventions}
                            onChange={setInventions}
                        />
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
                            className="btn btn-primary"
                            type="submit"
                        >
                            Create Inventor
                        </button>
                    </div>
                </form>
            </FormLayout>
        </Layout>
    );
}
