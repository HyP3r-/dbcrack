import cython

from libc.string cimport memset


cdef extern from "openssl/md5.h":
    unsigned char *MD5(const unsigned char *d, size_t n, unsigned char *md)

cdef extern from "openssl/blowfish.h":
    ctypedef struct BF_KEY:
        unsigned long P[16 + 2]
        unsigned long S[4 * 256]
    void BF_set_key(BF_KEY *key, int len, const unsigned char *data)
    void BF_cbc_encrypt(const unsigned char *input, unsigned char *output,
                        long length, const BF_KEY *schedule,
                        unsigned char *ivec, int enc)


cdef char probe(unsigned char[] password,
                unsigned char password_length,
                unsigned char[16] input_data):
    cdef unsigned char[16] digest
    cdef unsigned char[16] output_data
    cdef unsigned char[8] ivec
    cdef BF_KEY bf_key
    cdef int i
    # Create the hash
    MD5(password, password_length, digest)
    # Decrypt
    BF_set_key(&bf_key, 16, digest)
    memset(ivec, 0, 8)
    memset(output_data, 0, 16)
    BF_cbc_encrypt(input_data, output_data, 16, &bf_key, ivec, 0)
    # Give Result back
    for i in range(0, 16):
        if digest[i] != output_data[i]:
            return 0
    return 1

@cython.cdivision(True)
def probe_start(int password_start,
                int password_end,
                unsigned char[] letters,
                unsigned char[] data):
    # TODO 20 has to be improved
    # Variables
    cdef unsigned char[20] password
    cdef int letter_length = len(letters)
    cdef int password_length = 0
    cdef int temp_i, password_i, modulus
    cdef char result = 0

    # Start of the generation of the passwords
    for i in range(password_start, password_end + 1):
        # Reset the variables
        temp_i = i
        password_i = 0
        # Generate Password
        # TODO Increment of the password has to be improved
        while True:
            modulus = temp_i % letter_length
            temp_i /= letter_length
            password[password_i] = letters[modulus]
            if temp_i == 0:
                break
            password_i += 1
        # Test password
        result = probe(password,
                       password_i + 1,
                       data)
        # Return result
        if result == 1:
            return result, password[:password_i + 1]
    return result, ""

