import React from 'react'
import {
  SafeAreaView, TouchableOpacity, Text
} from 'react-native'
import { NativeModules } from 'react-native'

const { ToolsManager } = NativeModules
const App = () => {
  React.useEffect(() => {
    

    ToolsManager.test()
    ToolsManager.testSimple("name", 1123, true)
    ToolsManager.testComplex([1, 2, 3, 4], { a: 123, b: 'das' })
    ToolsManager.testCallback((num: number, str: string, arr: string[], obj: any) => {
      console.log(num, str, arr, obj)
    })
    testPromise()
    console.log('ToolsManager.SUCCESS: ', ToolsManager.SUCCESS);
    console.log('ToolsManager.ERROR: ', ToolsManager.ERROR);
  }, [])
  const testPromise = async () => {
    try {
      const res = await ToolsManager.testPromise(true)
      console.log(res)

    } catch (error) {
      console.log(error)
    }

    try {
      const res = await ToolsManager.testPromise(false)
      console.log(res)

    } catch (error) {
      console.log(error.message)
    }
  }
  const openFile = () => {
    ToolsManager.testToFile(() => {

    })
  }
  return (
    <SafeAreaView style={{ justifyContent: 'center', alignItems: 'center', flex: 1 }}>
      <TouchableOpacity style={{ width: 100, height: 30 }} onPress={openFile}>
        <Text>open file</Text>
      </TouchableOpacity>
    </SafeAreaView>
  )
}

export default App
