import React from "react";
import { Link } from "react-router-dom";
import { SidebarProps } from "./INavbar.ts";
const SidebarBase: React.FC<SidebarProps> = ({icons, labels, paths }) => {
    return (
        <>
            <ul className="nav nav-pills">
                {icons.map((Icon, index) => (
                    <li className="nav-item" key={index}>
                        <Link
                            to={paths[index]}
                            className="nav-link"
                            aria-current={index === 0 ? "page" : undefined}
                        >
                            <Icon style={{ fontSize: '20px', marginRight: '8px' }} /> {}
                            {labels[index]}
                        </Link>
                    </li>
                ))}
            </ul>
        </>
)
    ;
};

export default SidebarBase;
