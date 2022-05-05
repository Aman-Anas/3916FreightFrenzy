local weblit = require('weblit')
local fs = require("fs")
weblit.app.bind({
	host = "localhost",
	port = 8484
})
.use(weblit.logger)
.use(weblit.autoHeaders)
.route({
	method = "GET",
	path = "/"
}, function(req, res, go)
	res.code = 200
	res.headers["Content-Type"] = "text/x-lua"
	res.body = fs.readFileSync("botscript.lua")
end)
.start()