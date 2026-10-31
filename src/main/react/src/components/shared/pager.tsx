type Props = {
    currentPage: number;
    lastPage: number;
    pageSetter: React.Dispatch<React.SetStateAction<number>>;
};

export default function Pager({ currentPage, lastPage, pageSetter }: Props) {
    return <>
        { currentPage > 1 ?
            <button
                className={"secondary-btn"}
                onClick={() => pageSetter(currentPage - 1)}
            >Back</button>
            :
            <></>
        }
        { lastPage > currentPage ?
            <button
                className={"secondary-btn"}
                onClick={() => pageSetter(currentPage + 1)}
            >Next</button>
            :
            <></>
        }
    </>
}
