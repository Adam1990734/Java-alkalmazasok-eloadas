import type { ReactNode } from "react";

type Props = {
    name: string,
    children: ReactNode
}
export default function FormLayout({ children, name }: Props) {
    return (
        <div className="container mt-4">
            <div className="row justify-content-center">
                <div className="col-lg-8 col-xl-7">
                    <div className="card shadow-sm border-0">
                        <div className="card-header bg-primary text-white">
                            <h4 className="mb-0">{name}</h4>
                        </div>

                        <div className="card-body p-4">
                            {children}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}