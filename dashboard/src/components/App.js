import React, { Component } from 'react';
import {Item,Grid}	 from 'semantic-ui-react';
import Chart from './Chart'

const URL = 'http://kotlin-kudos-backend.herokuapp.com/stats' || "http://localhost:8080/stats" 

class App extends Component {
  constructor(props) {
    super(props);
    setInterval(() => { 
	   this.fetchData(
		  (title, result) => {
			  var st = this.state;
			  st[title] = {type:'bar',name:title, data: result.map((m)=> {return {date:m.month, count:m.count}})};
			  this.setState(st);
			})}
			, 1000);
    this.state = {}
  }

  fetchData(cb) {
    fetch(URL+'/tweets/month').then(result=>result.json()).then((result) => cb('Twitter',result));
    fetch(URL+'/tweets/month').then(result=>result.json()).then((result) => cb('StackOverflow',result));
  }

 componentDidMount() {
 }

  render() {
    return (
      <div>
        <Grid columns={4}>
         {Object.keys(this.state).map((key,idx) => {
	    const item = this.state[key];
	    return <Item key={idx} title={item.name}>
        <Item.Content verticalAlign="middle">
          <Item.Header>{item.name}</Item.Header>
          <Item.Extra>
          <Chart type={item.type} data={item.data}/>
		</Item.Extra>
        </Item.Content>
        </Item>})}
        </Grid>
      </div>
    );
  }
}

export default App;