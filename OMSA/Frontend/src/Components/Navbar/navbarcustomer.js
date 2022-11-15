import React, { useState } from 'react';
import { useNavigate } from "react-router-dom";
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
        <Navbar.Brand href='./home'>Online Mobile Store</Navbar.Brand>
        <Nav className="me-auto">
          <Link className="links" style={linkStyle} to='../home'>Home</Link>
          <Link className="links" style={linkStyle} to='../mobile'>Store</Link>
          <Link className="links" style={linkStyle} to='../customer'>My Details</Link>
          <Link className="links" style={linkStyle} to='../order'>My Orders </Link>
          <Link className="links" style={linkStyle} to='../cart'>View my Cart</Link>
        </Nav>
        <Navbar.Brand className="links" href='../'>Log Out</Navbar.Brand>
      </Container>
    </Navbar>
  );
}
