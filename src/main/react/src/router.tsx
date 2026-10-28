import { createBrowserRouter } from 'react-router-dom';
import InventorIndexView from "./pages/inventor";
import InventorCreateView from "./pages/inventor/create.tsx";
import InventorDetailsView from "./pages/inventor/details.tsx";
import InventorUpdateView from "./pages/inventor/update.tsx";
import InventorDeleteView from "./pages/inventor/delete.tsx";
import App from './App.tsx';

export const router = createBrowserRouter([
    {
        path: '/crud',
        element: <App/>
    },
    {
        path: '/crud/inventors',
        element: <InventorIndexView hasOperations={true}/>
    },
    {
        path: '/crud/inventors/create',
        element: <InventorCreateView/>
    },
    {
        path: '/crud/inventors/:id',
        element: <InventorDetailsView/>
    },
    {
        path: '/crud/inventors/update/:id',
        element: <InventorUpdateView/>
    },
    {
        path: '/crud/inventors/delete/:id',
        element: <InventorDeleteView/>
    }
]);