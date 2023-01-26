import Navbar from "react-bootstrap/Navbar";
import logo from "../assets/img/ltnLogo.jpg";

const NavHome = () => {

    return(
        <>
            <Navbar.Brand href="/">
                <img
                    src={logo}
                    className="logo"
                    alt="Logo"
                />
            </Navbar.Brand>
        </>
    );
};

export default NavHome;