all:
	python3 cython_setup.py build_ext --inplace

clean:
	rm -rf build __pycache__
	rm -rf *.so *.o *.c
