const { Router } = require("express");
const { check } = require("express-validator");

const { add, remove } = require("../controllers/notesController");
const validateData = require("../middleware/validateData");

try {

    const router = new Router();
    const conditions = [
        check("sessionKey").notEmpty(),
        check("username").notEmpty(),
        check("date").notEmpty()
    ];

    router.post("/add", [ ...conditions, check("note").notEmpty() ], validateData, add);
    router.post("/remove", conditions, validateData, remove);

    module.exports = router;
} catch (err) {
    throw err;
}
