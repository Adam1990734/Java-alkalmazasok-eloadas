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
        <div className={"mb-3"}>
            <input
                type="text"
                className={"form-control"}
                maxLength={40}
                value={query}
                onChange={(e) => setQuery(e.target.value)}
                placeholder="Invention search..."
            />

            {suggestions.length > 0 && (
                <ul className="list-group">
                    {suggestions.map(invention => (
                        <li className="list-group-item list-group-item-action"
                            key={invention.id}
                            onClick={() => addInvention(invention)}
                            style={{
                                cursor: "pointer"
                            }}
                        >
                            {invention.name}
                        </li>
                    ))}
                </ul>
            )}

            {value.length > 0 && (
                <div>
                    <h4>Selected inventions</h4>

                    {value.map(invention => (
                        <div key={invention.id}>
                            {invention.name}

                            <button
                                className={"btn btn-secondary"}
                                type="button"
                                onClick={() => removeInvention(invention.id)}
                            >
                                X
                            </button>
                        </div>
                    ))}
                </div>
            )}
        </div>
    );
}