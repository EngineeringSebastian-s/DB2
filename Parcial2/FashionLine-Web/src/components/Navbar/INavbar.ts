export interface SidebarProps {
    icons: React.ElementType[];
    labels: string[];
    paths: string[];
}


export interface SidebarInterface {
    render(): JSX.Element;
}