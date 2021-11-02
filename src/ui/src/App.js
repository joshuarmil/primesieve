import logo from './sieve.png';
import './App.css';

import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Button, Input, InputGroup } from 'reactstrap';

class App extends React.Component {

  constructor() {
    super();
    this.state = {
      value: '',
      valueValid: undefined,
      submittedValue: 0,
      responseValid: false,
      primes: []
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleClick = this.handleClick.bind(this);
  }

  handleClick = async() => {
    if (this.state.valueValid) {
      this.setState({responseValid: false})
      const response = await fetch(`/primes?value=${this.state.value}`);
      const body = await response.json();
      if (this.validateResponse(body)) {
        this.setState({submittedValue: this.state.value, primes: body, responseValid: true});
      }
    } 
  }

  handleChange({ target }) {
    this.setState({
      value: target.value
    });

    if (target.value == "") {
      this.setState({valueValid: undefined})
    } else if (this.validateInput(target.value)) {
      this.setState({valueValid: true})
    } else {
      this.setState({valueValid: false})
    }
  }

  validateInput(value) {
    // RegEx testing that all characters are digits 0-9
    return /^\d+$/.test(value);
  }


  validateResponse(response) {
    return Array.isArray(response);
  }

  render() {
    const {submittedValue, primes} = this.state;

    function PrimeResults() {
      return (
        <div className="Prime-table">
          <h2>Total primes up to {submittedValue} : {primes.length}</h2>
          <div class="row row-cols-auto">
            {primes.map((prime, i) =>
                <div class="col col-lg-1" key={i}> {prime} </div>
            )}
          </div>
        </div>
      );
    }

    let results;

    if (this.state.responseValid) {
      results = <PrimeResults/>
    } else {
      results = <div></div>
    }

    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <h2>Generate Lesser or Equal Primes</h2>
          </header>
          <div className="App-body">
            <InputGroup className="Prime-form" >
              <Input 
                type="text" 
                name="inputBox" 
                className="form-control"
                placeholder="Enter integer here" 
                value={ this.state.value }
                onChange={ this.handleChange }
                id="validationDefault05"
                required
              />
              <Button value="Send" onClick={ this.handleClick }>Calculate</Button>
            </InputGroup>
            {this.state.valueValid == false ? <span style={{color: "red"}}>Please enter a valid integer</span> : ''}
            {results}
          </div>
        </div>
    );
  }
}
export default App;
