import type {InventionMinimal} from "./invention.ts";

export interface Inventor {
    type: "Inventor";
    id: number;
    name: string;
    bornat: number;
    diedat: number | null;
    inventions: InventionMinimal[];
}

export interface InventorResponse {
    id: number;
    name: string;
    bornat: number;
    diedat: number | null;
    inventions: InventionMinimal[];
}

export interface InventorMinimal {
    id: number,
    name: string
}

export interface InventorRequest {
    name: string;
    bornat: number;
    diedat: number | null;
    inventions: number[]
}
