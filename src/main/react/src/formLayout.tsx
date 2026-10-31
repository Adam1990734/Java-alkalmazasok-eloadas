import type { ReactNode } from "react";

type Props = {
    name: string,
    children: ReactNode
}
export default function FormLayout({ children, name }: Props) {
    return <div className={"card"}>
        <div className={"card-header"}>
            { name }
        </div>
        <div>
            { children }
        </div>
    </div>
}