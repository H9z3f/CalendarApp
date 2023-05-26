const { Router } = require("express");
const { check } = require("express-validator");

const { registration, login } = require("../controllers/authorizationController");
const validateData = require("../middleware/validateData");
const encryptPassword = require("../middleware/encryptPassword");
const comparePassword = require("../middleware/comparePassword");
const createSession = require("../middleware/createSession");

try {
    const router = new Router();
    const conditions = [
        check("username").notEmpty(),
        check("password").notEmpty()
    ];

    router.post("/registration", conditions, validateData, encryptPassword, createSession, registration);
    router.post("/login", conditions, validateData, comparePassword, createSession, login);

    module.exports = router;
} catch (err) {
    throw err;
}
