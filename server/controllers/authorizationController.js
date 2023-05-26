const config = require("config");
const mongoose = require("mongoose");

const User = require("../models/User");
const Note = require("../models/Note");

class AuthorizationController {
    async registration(req, res) {
        try {
            const { sessionKey } = req.body;

            await mongoose.connect(config.get("server.database.uri"));

            const userObject = new User(req.body);
            await userObject.save();

            res.send(JSON.stringify({ success: true, sessionKey, notes: {} }));
        } catch (err) {
            res.send(JSON.stringify({ success: false, description: "Non-unique username..." }));
        } finally {
            await mongoose.disconnect();
        }
    }

    async login(req, res) {
        try {
            const { username, sessionKey } = req.body;
            const notes = {};

            await mongoose.connect(config.get("server.database.uri"));

            const userObject = await User.updateOne({ username }, { sessionKey });
            const noteObject = await Note.find({ username }, { _id: false, date: true, note: true });

            for (let { date, note } of noteObject) {
                notes[date] = note;
            }

            res.send(JSON.stringify({ success: true, sessionKey, notes }));
        } catch (err) {
            res.send(JSON.stringify({ success: false, description: "Invalid username or password..." }));
        } finally {
            await mongoose.disconnect();
        }
    }
}

module.exports = new AuthorizationController();
