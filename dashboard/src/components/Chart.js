import React, {Component} from 'react';
import { VictoryLabel, VictoryPie, VictoryBar, VictoryChart, VictoryAxis, VictoryTheme } from 'victory';

class Chart extends Component {

  renderBar() {
    return (<VictoryChart domainPadding={40} theme={VictoryTheme.material} colorScale={"warm"}>
       <VictoryAxis fixLabelOverlap={true} tickLabelComponent={<VictoryLabel angle={90} dx={25} height={300}/>} />
        <VictoryBar x="date" y="count" data={this.props.data}/>
       </VictoryChart>)
  }

  render() {
    if (this.props.type === 'bar') {
        return this.renderBar();
    }
    return null;
  }
}

export default Chart;