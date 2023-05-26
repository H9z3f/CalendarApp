const config = require("config");
const mongoose = require("mongoose");
const bcrypt = require("bcrypt");

const User = require("../models/User");

module.exports = async function (req, res, next) {
    try {
        const { username, password } = req.body;

        await mongoose.connect(config.get("server.database.uri"));

        const userObject = await User.findOne({ username });

        await bcrypt.compare(password, userObject.password, function (err, result) {
            if (err || !result) {
                return res.send(JSON.stringify({ success: false, description: "Invalid username or password..." }));
            }

            next();
        });
    } catch (err) {
        res.send(JSON.stringify({ success: false, description: "Invalid username or password..." }));
    } finally {
        await mongoose.disconnect();
    }
}
