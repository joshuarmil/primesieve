import { fireEvent, render, screen } from '@testing-library/react';
 
import App from './App';
 
test('render error on invalid input', () => {
  render(<App />);

  const inputEl = screen.getByRole('textbox', { placeholder: "inputBox" });
  expect(inputEl).toBeInTheDocument();
  expect(inputEl).toHaveAttribute("type", "text");

  //userEvent.type(inputEl, "test");
  fireEvent.change(inputEl, { target: {value: "test"}});

  expect(inputEl).toHaveValue("test");
  const resultElement = screen.getByText(/Please enter a valid integer/i);
  expect(resultElement).toBeInTheDocument();
});