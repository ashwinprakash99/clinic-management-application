import React, {useState} from 'react';
import './App.css';
import axios from 'axios';

function App() {
  const [rows, setRows] = useState([]);

  const [id, setId] = useState(0);

  const handleSubmit = event => {
    event.preventDefault();
    let url = `comps/${id}`;
    axios.get(url)
      .then(result => {
        let data = result.data;
        let rrows = [];
        let tempcell = [];
        tempcell.push(<th>Date</th>);
        tempcell.push(<th>Complain 1</th>);
        tempcell.push(<th>Complain 2</th>);
        tempcell.push(<th>Complain 3</th>);
        tempcell.push(<th>Explanation 1</th>);
        tempcell.push(<th>Explanation 2</th>);
        tempcell.push(<th>Explanation 3</th>);
        rrows.push(<tr>{ tempcell }</tr>);
        for (let i = 0; i < data.length; ++i) {
          let cell = [];
          let d = new Date(data[i].created_at);
          let s = `${d.getDate()}-${d.getMonth()+1}-${d.getFullYear()} ${d.getHours()}:${d.getMinutes()}:${d.getSeconds()}`;
          cell.push(<td>{ s }</td>);
          cell.push(<td>{ data[i].complaint1 }</td>);
          cell.push(<td>{ data[i].complaint2 }</td>);
          cell.push(<td>{ data[i].complaint3 }</td>);
          cell.push(<td>{ data[i].explanation1 }</td>);
          cell.push(<td>{ data[i].explanation2 }</td>);
          cell.push(<td>{ data[i].explanation3 }</td>);
          rrows.push(<tr>{ cell }</tr>);
        }
        setRows(rrows);
      }).then(result=> {
        setId(0);
      })
      .catch(err => {
        console.log(err);
      });
  };

  const handleChange = event => {
    setId(event.target.value);
  };

  return (
    <div className="wrapper">
      <h1>Query Patient Recent Complaints</h1>
      <form onSubmit={handleSubmit}>
        <fieldset>
          <label>
            <p>Patient ID:</p>
            <input name="id" onChange={handleChange} />
          </label>
        </fieldset>
        <button type="submit">Submit</button>
      </form>
      <div className="result">
      <h2>Query Results</h2>
      <table className="tabletag">
        { rows }
      </table>
      </div>
    </div>
  );
}


export default App;
