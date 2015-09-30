# dbcrack
Distributed Password BruteForce for DBISAM

## Configuration
`dbcrack` can be configured with the file `config.py`:
- `SERVER_IP` and `SERVER_PORT` should changed to the ip address of your server
- extract out of the database file the password hash and set `DATA` to that
  value. 

## Compilation
To enhance the bruteforce performance `dbcrack` uses cython. To compile the
tester run `make all`.
 
## Usage
1. Start the server with `python3 server.py`
2. Start the clients with `python3 worker.py`

## License
The MIT License (MIT)

Copyright (c) 2014 HyP3r

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
