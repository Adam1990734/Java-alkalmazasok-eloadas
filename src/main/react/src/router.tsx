import { createBrowserRouter } from 'react-router-dom';
import InventorIndexView from "./pages/inventor";
import InventorCreateView from "./pages/inventor/create.tsx";
import InventorDetailsView from "./pages/inventor/details.tsx";
import InventorUpdateView from "./pages/inventor/update.tsx";
import InventorDeleteView from "./pages/inventor/delete.tsx";
import App from './App.tsx';

export const router = createBrowserRouter([
    {
        path: '/',
        element: <App/>
    },
    {
        path: '/inventors',
        element: <InventorIndexView hasOperations={true}/>
    },
    {
        path: '/inventors/create',
        element: <InventorCreateView/>
    },
    {
        path: '/inventors/:id',
        element: <InventorDetailsView/>
    },
    {
        path: '/inventors/update/:id',
        element: <InventorUpdateView/>
    },
    {
        path: '/inventors/delete/:id',
        element: <InventorDeleteView/>
    }
]);