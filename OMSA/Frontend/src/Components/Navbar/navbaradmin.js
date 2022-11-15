import React, { useState } from 'react';
import "./navbar.css"
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { Link } from 'react-router-dom'
export default function App() {
  const linkStyle = {
    margin: "1rem",
    textDecoration: "none",
    color: 'white'
  };

  return (
    <Navbar bg="dark" variant="dark" style={{ position: "fixed", top: "0" }}>
      <Container style={{ position: "fixed" }}>
        <Navbar.Brand href='./homeadmin'>Online Mobile Store</Navbar.Brand>
        <Nav className="me-auto">
          <Link className="links" style={linkStyle} to='../homeadmin'>Home</Link>
          <Link className="links" style={linkStyle} to='../mobileadmin'>Mobiles</Link>
          <Link className="links" style={linkStyle} to='../useradmin'>Users</Link>
          <Link className="links" style={linkStyle} to='../customeradmin'> Customers</Link>
          <Link className="links" style={linkStyle} to='../orderadmin'>Orders</Link>
          <Link className="links" style={linkStyle} to='../cartadmin'>Carts</Link>
        </Nav>
        <Navbar.Brand className="links" href='../'>Log Out</Navbar.Brand>
      </Container>
    </Navbar>
  )
}

