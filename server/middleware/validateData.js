const { validationResult } = require("express-validator");

module.exports = async function (req, res, next) {
    try {
        res.setHeader("Content-Type", "application/json");

        const result = await validationResult(req);

        if (!result.isEmpty()) {
            return res.send(JSON.stringify({ success: false, description: result.mapped() }));
        }

        next();
    } catch (err) {
        res.send(JSON.stringify({ success: false }));
    }
}
