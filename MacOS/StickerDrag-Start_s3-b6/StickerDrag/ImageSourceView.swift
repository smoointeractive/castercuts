/*
 * Copyright (c) 2016 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */


import Cocoa

class ImageSourceView: RoundedRectView {
  // FFR(for future reference) "with" found below is an argument label
  override func mouseDown(with theEvent:NSEvent) {
    
    // 1. Creates an NSPasteboardItem and sets this class as its dataProvider.
    //    A NSPasteboardItem is the box that carries the info about the item being dragged. The
    //    NSPasteboardItemDataProvider provided data upon request. In the case you'll
    //    supply TIFF data, which is the standard way to carry images around Cocoa.
    let pasteboardItem = NSPasteboardItem()
    pasteboardItem.setDataProvider(self, forTypes: [kUTTypeTIFF])
    
    // 2. Creates a NSDraggingItem and assigns the pasteboard item to it. A dragging item
    //    exists to provide the drag image and carry one pasteboard item, but you dont keeep
    //    a reference to the item because of its limited lifespan. If needed, the dragging
    //    session will recreate this object. snapshot() is one of the helper methods
    //    mentioned earlier.  It creates an NSImage of an NSView.
    let draggingItem = NSDraggingItem(pasteboardWriter: pasteboardItem)
    draggingItem.setDraggingFrame(self.bounds, contents: snapshot())
    
    // 3. Starts the dragging session. Here you trigger the dragging image to start
    //    following you mouse until you drop it.
    beginDraggingSession(with: [draggingItem], event: theEvent, source:self)
  }
  
}

// MARK: - NSDraggingSource
extension ImageSourceView: NSDraggingSource {
  // 1. This method is required by NSDraggingSource. It tells thedragging session what sort of operation 
  //    you're attempting when the user drags from the view. In this case it's a generic operation.
  func draggingSession(_ session: NSDraggingSession, sourceOperationMaskFor context: NSDraggingContext) -> NSDragOperation {
    return .generic
  }
}

// MARK: - NSDraggingSource
extension ImageSourceView: NSPasteboardItemDataProvider {
  // 2. This implements the mandatory NSPasteboardItemDataProvider method. More on this soon --
  //    for now it's just a stub
  func pasteboard(_ pasteboard: NSPasteboard?, item:NSPasteboardItem, provideDataForType type: String) {
    //TODO: Return image data
    
    // 1. If the desired data type is kUTTypeTIFF, you load an inmage named unicorn
    if let pasteboard = pasteboard, type == String(kUTTypeTIFF), let image = NSImage(named:"unicorn") {
      // 2. Use one of the supplied helpers to tin the image with a random color. after all, colorfull 
      //    unicorns are more festive tha a smattering of all-vlack unicorns
      let finalImage = image.tintedImageWithColor(NSColor.randomColor())
      // 3. Transform the image into TIFF data and places it on the pasteboard
      let tiffdata = finalImage.tiffRepresentation
      pasteboard.setData(tiffdata, forType: type)
    }
  }
}
