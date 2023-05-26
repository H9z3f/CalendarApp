const config = require("config");
const express = require("express");
const bodyParser = require("body-parser");

const authorizationRouter = require("./routers/authorizationRouter");
const notesRouter = require("./routers/notesRouter");

try {
    const app = express();
    const port = process.env.PORT || config.get("server.port");

    app.use(bodyParser.json());

    app.use("/authorization", authorizationRouter);
    app.use("/notes", notesRouter);

    app.listen(port, () => console.log(`${new Date().toString()}: The server is listening on a port ${port}...`));
} catch (err) {
    console.log(err);
}
