//////////////// ChugiTree Class //////////////////////////
//
// Title: P09 Chugidex
// Course: CS 300 Fall 2022
//
// Author: Ria Sharma
// Email: rsharma78@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Alexander Kalis
// Partner Email: akalis@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// _X_ Write-up states that pair programming is allowed for this assignment.
// _X_ We have both read and understand the course Pair Programming Policy.
// _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:
// Online Sources: ZyBooks
//
///////////////////////////////////////////////////////////////////////////////
import java.util.NoSuchElementException;

/**
 * This class implements a ChugidexStorage as a Binary Search Tree.
 * 
 * Notes: 1) You may NOT use any arrays or Collections objects (ArrayLists, etc) in this class. 2)
 * You may NOT use any loops (for, while, etc) in this class. Recursive strategies only.
 *
 */
public class ChugiTree implements ChugidexStorage {

  /**
   * The root of this ChugiTree. Set to null when tree is empty.
   */
  private BSTNode<Chugimon> root;

  /**
   * The size of this ChugiTree (total number of Chugimon stored in this BST)
   */
  private int size;

  /**
   * Constructor for Chugitree. Should set size to 0 and root to null.
   */
  public ChugiTree() {
    this.size = 0;
    this.root = null;
  }

  /**
   * Getter method for the Chugimon at the root of this BST.
   * 
   * @return the root of the BST.
   */
  public Chugimon getRoot() {
    return this.root.getData();
  }

  /**
   * A method for determining whether this ChugiTree is a valid BST. In order to be a valid BST, the
   * following must be true: For every internal (non-leaf) node X of a binary tree, all the values
   * in the node's left subtree are less than the value in X, and all the values in the node's right
   * subtree are greater than the value in X.
   * 
   * @return true if this ChugiTree is a valid BST, false otherwise
   */
  public boolean isValidBST() {
    return isValidBSTHelper(root);
  }

  /**
   * A helper method for determining whether this ChugiTree is a valid BST. In order to be a valid
   * BST, the following must be true: For every internal (non-leaf) node X of a binary tree, all the
   * values in the node's left subtree are less than the value in X, and all the values in the
   * node's right subtree are greater than the value in X.
   * 
   * @param node The root of the BST.
   * @return true if the binary tree rooted at node is a BST, false otherwise
   */
  public static boolean isValidBSTHelper(BSTNode<Chugimon> node) {
    if (node == null) {
      return true;
    }
    if (node.getLeft() == null && node.getRight() == null)
      return true;
    if (node.getLeft() != null && getLargest(node.getLeft()).compareTo(node.getData()) > 0)
      return false;
    if (node.getRight() != null && getSmallest(node.getRight()).compareTo(node.getData()) < 0)
      return false;

    return isValidBSTHelper(node.getLeft()) && isValidBSTHelper(node.getRight());
  }

  /**
   * helper method for isValidBSTHelper
   * 
   * @return max
   */
  private static Chugimon getLargest(BSTNode<Chugimon> node) {
    Chugimon lMax;
    Chugimon rMin;
    Chugimon max = node.getData();

    if (node.getLeft() != null) {
      lMax = getLargest(node.getLeft());
      if (max.compareTo(lMax) < 0)
        max = lMax;
    }
    if (node.getRight() != null) {
      rMin = getLargest(node.getRight());
      if (max.compareTo(rMin) < 0)
        max = rMin;
    }
    return max;

  }

  /**
   * helper method for isValidBSTHelper
   * 
   * @return min
   */
  private static Chugimon getSmallest(BSTNode<Chugimon> node) {
    Chugimon lMin;
    Chugimon rMin;
    Chugimon min = node.getData();

    if (node.getLeft() != null) {
      lMin = getLargest(node.getLeft());
      if (min.compareTo(lMin) > 0)
        min = lMin;
    }
    if (node.getRight() != null) {
      rMin = getLargest(node.getRight());
      if (min.compareTo(rMin) > 0)
        min = rMin;
    }
    return min;

  }

  /**
   * Checks whether this ChugiTree is empty or not
   * 
   * @return true if this tree is empty, false otherwise
   */
  @Override
  public boolean isEmpty() {
    if (this.size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Gets the size of this ChugiTree
   * 
   * @return the total number of Chugimons stored in this tree
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns a String representation of all the Chugimons stored within this ChugiTree in the
   * increasing order with respect to the result of Chugimon.compareTo() method. The string should
   * be a comma-separated list of all the Chugimon toString() values. No spaces are expected to be
   * in the resulting string. No comma should be at the end of the resulting string. For instance,
   * 
   * "nameOne#12.25,nameTwo#12.56,nameTwo#89.27"
   * 
   * @return a string containing all of the Chugimon, in the increasing order. Returns an empty
   *         string "" if this BST is empty.
   * 
   */
  @Override
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the ChugiTree rooted at node.
   * An example of the String representation of the contents of a ChugiTree storing three Chugimons
   * is provided in the description of the above toString() method.
   * 
   * @param node references the root of a subtree
   * @return a String representation of all the Chugimons stored in the sub-tree rooted at node in
   *         increasing order. Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Chugimon> node) {
    String s = "";
    if (node == null) {
      return "";
    }
    s += toStringHelper(node.getLeft()).toString();
    s += node.getData().toString() + ",";
    s += toStringHelper(node.getRight()).toString();
    return s.substring(0, s.length());
  }

  /**
   * Adds a new Chugimon to this ChugiTree. Duplicate Chugimons are NOT allowed.
   * 
   * @param newChugimon Chugimon to add to this ChugiTree
   * @return true if if the newChugimon was successfully added to the ChugiTree, false if a match
   *         with newChugimon is already present in the tree.
   * @throws IllegalArgumentException with a descriptive error message if newChugimon is null.
   */
  @Override
  public boolean add(Chugimon newChugimon) throws IllegalArgumentException {
    if (newChugimon == null) {
      throw new IllegalArgumentException("chugimon is null");
    }
    if (root == null) {
      this.root = new BSTNode<Chugimon>(newChugimon);
      this.size++;
      return true;
    } else if (addHelper(newChugimon, root)) {
      this.size++;
      return true;
    }
    return false;
  }

  /**
   * Recursive helper method to insert a new Chugimon to a Pokedex rooted at node.
   * 
   * @param node        The "root" of the subtree we are inserting the new Chugimon into.
   * @param newChugimon The Chugimon to be added to a BST rooted at node. We assume that newChugimon
   *                    is NOT null.
   * @return true if the newChugimon was successfully added to the ChugiTree, false if a match with
   *         newChugimon is already present in the subtree rooted at node.
   */
  protected static boolean addHelper(Chugimon newChugimon, BSTNode<Chugimon> node) {
    if (newChugimon.compareTo(node.getData()) > 0 && node.getRight() == null) {
      node.setRight(new BSTNode<Chugimon>(newChugimon));
      return true;
    } else if (newChugimon.compareTo(node.getData()) < 0 && node.getLeft() == null) {
      node.setLeft(new BSTNode<Chugimon>(newChugimon));
      return true;
    } else if (newChugimon.compareTo(node.getData()) > 0 && node.getRight() != null) {
      return addHelper(newChugimon, node.getRight());
    } else if (newChugimon.compareTo(node.getData()) < 0 && node.getLeft() != null) {
      return addHelper(newChugimon, node.getLeft());
    }
    return false;
  }

  /**
   * Searches a Chugimon given its first and second identifiers.
   * 
   * @param firstId  First identifier of the Chugimon to find
   * @param secondId Second identifier of the Chugimon to find
   * @return the matching Chugimon if match found, null otherwise.
   */
  @Override
  public Chugimon lookup(int firstId, int secondId) {
    Chugimon toFind = new Chugimon(firstId, secondId);
    return lookupHelper(toFind, root);
  }

  /**
   * Recursive helper method to search and return a match with a given Chugimon in the subtree
   * rooted at node, if present.
   * 
   * @param toFind a Chugimon to be searched in the BST rooted at node. We assume that toFind is NOT
   *               null.
   * @param node   "root" of the subtree we are checking whether it contains a match to target.
   * @return a reference to the matching Chugimon if found, null otherwise.
   */
  protected static Chugimon lookupHelper(Chugimon toFind, BSTNode<Chugimon> node) {
    if (node == null) {
      return null;
    }
    if (toFind.equals(node.getData())) {
      return node.getData();
    }
    BSTNode<Chugimon> tnode = node;
    if (toFind.compareTo(tnode.getData()) < 0) {
      tnode = node.getLeft();
      return lookupHelper(toFind, tnode);
    }
    tnode = node.getRight();
    return lookupHelper(toFind, tnode);

  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(this.root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at node counting the
   * number of nodes and NOT the number of edges from node to the deepest leaf
   * 
   * @param node root of a subtree
   * @return height of the subtree rooted at node
   */
  protected static int heightHelper(BSTNode<Chugimon> node) {
    int tracker = 0;
    BSTNode<Chugimon> tRoot = node;
    if (tRoot.getLeft() != null) {
      tracker++;
      tRoot = tRoot.getLeft();
      heightHelper(tRoot);
    }
    if (tRoot.getRight() != null) {
      tracker++;
      tRoot = tRoot.getRight();
      heightHelper(tRoot);
    }
    return tracker;
  }

  /**
   * Recursive method to find and return the first Chugimon, in the increasing order, within this
   * ChugiTree (meaning the smallest element stored in the tree).
   * 
   * @return the first element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getFirst() {
    // HINT: The smallest element in a non-empty BST is the left most element
    if (this.isEmpty()) {
      return null;
    }
    return getFirstHelper(this.root);
  }

  /**
   * Recursive helper method for getFirst().
   * 
   * @param root the node from which to find the the minimum node
   * @return the minimum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getFirstHelper(BSTNode<Chugimon> root) {
    // HINT: The smallest element in a non-empty BST is the left most element
    BSTNode<Chugimon> l = root;
    if (root.getLeft() != null) {
      l = l.getLeft();
      getFirstHelper(l);
    }
    return l.getData();
  }

  /**
   * Recursive method to find and return the last Chugimon, in the increasing order, within this
   * ChugiTree (meaning the greatest element stored in the tree).
   * 
   * @return the last element in the increasing order of this BST, and null if the tree is empty.
   */
  @Override
  public Chugimon getLast() {
    // HINT: The greatest element in a non-empty BST is the right most element
    if (this.isEmpty()) {
      return null;
    }
    return getLastHelper(this.root);
  }

  /**
   * Recursive helper method for getLast().
   * 
   * @param root the node from which to find the the maximum node
   * @return the maximum element in the increasing order from the node <b>root</b>
   */
  protected static Chugimon getLastHelper(BSTNode<Chugimon> root) {
    // HINT: The smallest element in a non-empty BST is the right most element
    BSTNode<Chugimon> r = root;
    if (root.getRight() != null) {
      r = r.getRight();
      getLastHelper(r);
    }
    return r.getData();
  }

  /**
   * Recursive method to get the number of Chugimon with a primary or secondary type of the
   * specified type, stored in this ChugiTree.
   * 
   * @param chugiType the type of Chugimons to count. We assume that chugiType is NOT null.
   * @return the number of all the Chugimon objects with a primary or secondary type of the
   *         specified type stored in this ChugiTree.
   */
  public int countType(ChugiType chugiType) {
    int tracker = 0;
    BSTNode<Chugimon> tRoot = this.root;
    if (tRoot.getLeft() != null) {
      if (tRoot.getLeft().getData().getPrimaryType() == chugiType
          || tRoot.getLeft().getData().getSecondaryType() == chugiType) {
        tracker++;
        tRoot = tRoot.getLeft();
      }
      countType(chugiType);
    }
    if (tRoot.getRight() != null) {
      if (tRoot.getRight().getData().getPrimaryType() == chugiType
          || tRoot.getRight().getData().getSecondaryType() == chugiType) {
        tracker++;
        tRoot = tRoot.getRight();
      }
      countType(chugiType);
    }
    return tracker;
  }

  /**
   * Finds and returns the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its successor
   * @return the in-order successor of a specified Chugimon in this ChugiTree
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   with a descriptive error message if the Chugimon provided as
   *                                  input has no in-order successor in this ChugiTree.
   */
  @Override
  public Chugimon next(Chugimon chugi) throws IllegalArgumentException, NoSuchElementException {
    if (chugi == null) {
      throw new IllegalArgumentException("chugi is null");
    } else if (nextHelper(chugi, this.root, null) == null) {
      throw new NoSuchElementException("no successor");
    } else {
      return nextHelper(chugi, this.root, null);
    }
  }

  /**
   * Recursive helper method to find and return the next Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order successor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param next  a BSTNode which stores a potentional candidate for next node
   * @return the next Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order successor in the subtree rooted at node.
   */
  protected static Chugimon nextHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> next) throws NoSuchElementException {
    // Hint: you will need to use getFirstHelper in this method. Below are
    // additional hints.


    // base case: node is null
    if (node == null) {
      throw new NoSuchElementException("no next chugi");
    }
    // recursive cases:
    // (1) if chugi is found and if the right child is not null, use getFirstHelper
    // to find and
    // return the next chugimon. It should be the left most child of the right
    // subtree

    // (2) if chugi is less than the Chugimon at node, set next as the root node and
    // search
    // recursively into the left subtree
    if (chugi.equals(node.getData())) {
      if (node.getRight() != null) {
        next = new BSTNode<Chugimon>(getFirstHelper(node.getRight()));
        return getFirstHelper(node.getRight());
      } else if (node.getRight() == null) {
        if (next == null) {
          return getFirstHelper(node.getRight());
        }
        return next.getData();
      }
    } else if (chugi.compareTo(node.getData()) > 0) {
      return nextHelper(chugi, node.getRight(), next);
    }
    return nextHelper(chugi, node.getLeft(), node);
  }



  /**
   * Finds and returns the in-order predecessor of a specified Chugimon in this ChugiTree
   * 
   * @param chugi the Chugimon to find its predecessor
   * @return the in-order predecessor of a specified Chugimon in this ChugiTree.
   * 
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   * @throws NoSuchElementException   if there is no Chugimon directly before the provided Chugimon
   */
  @Override
  public Chugimon previous(Chugimon chugi) {
    return previousHelper(chugi, root, null);
  }

  /**
   * Recursive helper method to find and return the previous Chugimon in the tree rooted at node.
   * 
   * @param chugi a Chugimon to search its in-order predecessor. We assume that <b>chugi</b> is NOT
   *              null.
   * @param node  "root" of a subtree storing Chugimon objects
   * @param prev  a BSTNode which stores a potentional candidate for previous node
   * @return the previous Chugimon in the tree rooted at node.
   * @throws NoSuchElementException with a descriptive error message if the Chugimon provided as
   *                                input has no in-order predecessor in the subtree rooted at node.
   */
  protected static Chugimon previousHelper(Chugimon chugi, BSTNode<Chugimon> node,
      BSTNode<Chugimon> prev) {
    // Hint: you will need to use getLastHelper in this method. Below are more
    // hints.

    // base case: node is null
    if (node == null) {
      throw new NoSuchElementException("no previous chugimon");
    }
    // recursive cases:
    // (1) if chugi is found and if the left child is not null, use getLastHelper to
    // find and return
    // the previous chugimon. It should be the right most child of the left subtree

    // (2) if chugi is greater than the Chugimon at node, set prev as the root node
    // and search
    // recursively into the right subtree

    if (chugi.compareTo(node.getData()) > 0) {
      prev = node;
      return previousHelper(chugi, node.getRight(), prev);
    } else if (chugi.compareTo(node.getData()) < 0) {
      return previousHelper(chugi, node.getLeft(), node);
    } else {
      if (prev != null) {
        return prev.getData();
      } else {
        throw new NoSuchElementException("no previous chugimon");
      }
    }
  }

  /**
   * Deletes a specific Chugimon from this ChugiTree.
   * 
   * @param chugi the Chugimon to delete
   * @return true if the specific Chugimon is successfully deleted, false if no match found with any
   *         Chugimon in this tree.
   * @throws IllegalArgumentException with a descriptive error message if <b>chugi</b> is null
   */
  @Override
  public boolean delete(Chugimon chugi) {
    try {
      this.root = deleteChugimonHelper(chugi, this.root);
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  /**
   * Recursive helper method to search and delete a specific Chugimon from the BST rooted at node
   * 
   * @param target a reference to a Chugimon to delete from the BST rooted at node. We assume that
   *               target is NOT null.
   * @param node   "root" of the subtree we are checking whether it contains a match with the target
   *               Chugimon.
   * 
   * 
   * @return the new "root" of the subtree we are checking after trying to remove target
   * @throws NoSuchElementException with a descriptive error message if there is no Chugimon
   *                                matching target in the BST rooted at <b>node</b>
   */
  protected static BSTNode<Chugimon> deleteChugimonHelper(Chugimon target, BSTNode<Chugimon> node) {
    if (node == null) {
      throw new NoSuchElementException("Empty ChugiTree. Chugimon can not be deleted.");
    }
    if (target.compareTo(node.getData()) == -1) {
      node.setLeft(deleteChugimonHelper(target, node.getLeft()));
    } else if (target.compareTo(node.getData()) == 1) {
      node.setRight(deleteChugimonHelper(target, node.getRight()));
    } else {
      if (node.getLeft() == null & node.getRight() == null) {
        node = null;
      } else if (node.getLeft() == null) {
        node = node.getRight();
      } else if (node.getRight() == null) {
        node = node.getLeft();
      } else {
        Chugimon min = getFirstHelper(node.getRight());
        node = new BSTNode<Chugimon>(min, node.getLeft(), node.getRight());
        node.setRight(deleteChugimonHelper(min, node.getRight()));
      }
    }
    return node;
  }

}
