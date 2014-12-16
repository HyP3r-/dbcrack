from distutils.core import setup
from distutils.extension import Extension

from Cython.Distutils import build_ext


setup(
    name='tester_dbisam',
    cmdclass={'build_ext': build_ext},
    ext_modules=[
        Extension(
            name="tester_dbisam",
            sources=["tester_dbisam.pyx"],
            libraries=["crypto"],
            library_dirs=["/usr/local/lib/", "/usr/lib",
                          "/lib/x86_64-linux-gnu"],
            extra_compile_args=['-O3'])
    ],
    requires=['blowfish']
)
