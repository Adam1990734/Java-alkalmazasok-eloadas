import type {PropsWithChildren} from "react";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function Layout({ children } : PropsWithChildren) {
    return <main>
        { children }
    </main>
}