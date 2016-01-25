package nongraded;

public class ComplexNumber {
	
	private double real;
	private double imaginary;
	
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}
	
	public double real() {
		return this.real;
	}
	
	public double imaginary() {
		return this.imaginary;
	}
	
	public ComplexNumber add(ComplexNumber c) {
		double newReal = this.real + c.real;
		double newImaginary = this.imaginary + c.imaginary;
		return new ComplexNumber(newReal, newImaginary);
	}
	
	public ComplexNumber multiply(ComplexNumber c) {
		double newReal = (this.real * c.real) - (this.imaginary + c.imaginary);
		double newImaginary = (this.real * c.imaginary) + (this.imaginary * c.real);
		return new ComplexNumber(newReal, newImaginary);
	}

}
