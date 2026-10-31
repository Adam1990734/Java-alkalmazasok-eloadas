import axios from "axios";
import { useEffect, useState } from "react";
import type { InventionMinimal } from "../../types/types.ts";

interface Props {
    value: InventionMinimal[];
    onChange: (inventions: InventionMinimal[]) => void;
}

export default function InventionSelector({
                                             value,
                                             onChange
                                         }: Props) {
    const [query, setQuery] = useState("");
    const [suggestions, setSuggestions] = useState<InventionMinimal[]>([]);

    useEffect(() => {
        if (query.trim().length < 2) {
            setSuggestions([]);
            return;
        }

        const timeout = setTimeout(async () => {
            try {
                const response = await axios.get<InventionMinimal[]>(
                    "http://localhost:8080/api/inventions/suggest",
                    {
                        params: {
                            name: query
                        }
                    }
                );

                setSuggestions(response.data);
            } catch (error) {
                console.error(error);
            }
        }, 400);

        return () => clearTimeout(timeout);
    }, [query]);

    const addInvention = (invention: InventionMinimal) => {
        if (value.some(i => i.id === invention.id)) {
            return;
        }

        onChange([...value, invention]);
        setQuery("");
        setSuggestions([]);
    };

    const removeInvention = (id: number) => {
        onChange(
            value.filter(i => i.id !== id)
        );
    };

    return (
        <div className="card border-0 shadow-sm">
            <div className="card-body">
                <label className="form-label fw-semibold">
                    Inventions
                </label>

                <input
                    type="text"
                    className="form-control"
                    maxLength={40}
                    value={query}
                    onChange={(e) => setQuery(e.target.value)}
                    placeholder="Search inventions..."
                />

                {suggestions.length > 0 && (
                    <ul className="list-group mt-2">
                        {suggestions.map((invention) => (
                            <li
                                key={invention.id}
                                className="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
                                onClick={() => addInvention(invention)}
                                style={{ cursor: "pointer" }}
                            >
                                <span>{invention.name}</span>

                                <span className="badge text-bg-primary">
                            Add
                        </span>
                            </li>
                        ))}
                    </ul>
                )}

                {value.length > 0 && (
                    <>
                        <hr />

                        <div className="mb-2">
                    <span className="fw-semibold">
                        Selected inventions
                    </span>
                        </div>

                        <div className="d-flex flex-wrap gap-2">
                            {value.map((invention) => (
                                <span
                                    key={invention.id}
                                    className="badge text-bg-success fs-6 d-inline-flex align-items-center"
                                >
                            {invention.name}

                                    <button
                                        type="button"
                                        className="btn-close btn-close-white ms-2"
                                        aria-label="Remove"
                                        onClick={() =>
                                            removeInvention(invention.id)
                                        }
                                    />
                        </span>
                            ))}
                        </div>
                    </>
                )}
            </div>
        </div>
    );
}