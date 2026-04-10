# 🎬 Online Movie Ticket Booking System

A scalable platform for searching movies and booking tickets — built for 100M+ daily active users.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-backend-brightgreen)
![REST API](https://img.shields.io/badge/API-REST-blue)
![License](https://img.shields.io/badge/license-MIT-yellow)

---

## 📋 Table of Contents
- [Overview](#overview)
- [Functional Features](#functional-features)
- [User Flow](#user-flow)
- [API Reference](#api-reference)
- [Core Entities](#core-entities)

---

## 📖 Overview

This system allows users to **search for movies** by title, location, and date — view show details — and **complete a booking** with seat selection and payment. Built with Spring Boot, it is designed to be highly available for browsing and strictly consistent for booking transactions.

---

## ✅ Functional Features

### 1. Browse & Search Movies ( Not Implemented for now using GET API to fetch sample movies)
- Search movies by title, location, and date
- Paginated results for fast browsing at scale
- Powered by Elasticsearch for low-latency queries (~200ms)

### 2. View Movie Details
- View full movie info: title, description, metadata
- Browse available shows with timing and venue details
- See available seat layout per show

### 3. Book a Ticket
- Reserve selected seats for a show
- Confirm booking with payment details
- Receive booking confirmation and notification
- Strictly consistent booking — no double-seat allocation

---

## 🔄 User Flow
Search Movie → View Details → Select Seats → Reserve → Pay & Confirm

---

## 🔌 API Reference

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/v1/shows | List movies with pagination |
| `POST` | `/shows/{showId}/book` 

---

## 🗂️ Core Entities

| Entity | Description |
|--------|-------------|
| **User** | Registered customer performing the booking |
| **Show** | A scheduled screening of a movie |
| **Theatre** | Venue hosting the show |
| **Ticket** | Confirmed booking record for a user |

---

> **Scope:** This README covers the browse & booking flows only.  
> For full system architecture, see [`/docs/system-design.md`](./docs/system-design.md).
