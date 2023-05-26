const { Schema, model } = require("mongoose");

const userSchema = new Schema({
    username: { type: String, require: true, unique: true },
    password: { type: String, require: true },
    sessionKey: { type: String }
});

module.exports = model("users", userSchema);
